(defproject conceit/conceit-commons "1.0.4"
  :description "Common libraries for Clojure."
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [javax.mail/mail "1.4.3"]
                 [mongoika "0.6.8"]]
  :dev-dependencies [[lein-ring "0.4.6"]
                     [swank-clojure "1.4.0"]]
  :aot [conceit.commons])
