(ns conceit.commons.mail
  (use conceit.commons)
  (import [java.util Properties]
          [javax.mail Session Message Message$RecipientType Authenticator PasswordAuthentication Transport]
          [javax.mail.internet MimeMessage InternetAddress]))

(defn send-mail! [& {:keys [host port ssl auth user password to from subject body charset]}]
  (when-not (empty? to)
    (let [properties (?-> (doto (Properties.)
                            (.put "mail.smtp.host" host)
                            (.put "mail.smtp.port" (str port))
                            (.put "mail.smtp.socketFactory.port" (str port))
                            (.put "mail.smtp.auth" (str auth)))
                          user (doto (.put "mail.smtp.user" user))
                          ssl (doto (.put "mail.smtp.starttls.enable" "true")
                                (.put "mail.smtp.socketFactory.class" "javax.net.ssl.SSLSocketFactory")
                                (.put "mail.smtp.socketFactory.fallback" "false")))
          session (if password
                    (Session/getInstance properties (proxy [Authenticator] []
                                                      (getPasswordAuthentication []
                                                        (PasswordAuthentication. user password))))
                    (Session/getInstance properties))
          recipients (apply str (interpose \, (?-> to string? vector)))]
      (Transport/send (doto (MimeMessage. session)
                        (.setFrom (InternetAddress. from))
                        (.setRecipients (Message$RecipientType/TO) (InternetAddress/parse recipients))
                        (.setSubject subject)
                        (.setContent body (?-> "text/plain" charset (str "; " charset)))))
      true)))
