(ns conceit.commons.test.http
  (use conceit.commons.http
       conceit.commons.test
       clojure.test))

(deftest* parse-query-string-test
  (= {"name" "hisashige" "age" "42"} (parse-query-string "name=hisashige&age=42"))
  (= {"name" "hisashige tanaka" "age" "42" "active" "true"} (parse-query-string "name=hisashige+tanaka&age=42&active=true"))
  (= {"name" "hisashige tanaka" "age" "42" "active" "true"} (parse-query-string "active=true&age=42&name=hisashige+tanaka"))
  (= {"uri" "http://localhost:8080/foo/bar?hoge=piyo&x=y" "name" "Jack"} (parse-query-string "uri=http%3A%2F%2Flocalhost%3A8080%2Ffoo%2Fbar%3Fhoge%3Dpiyo%26x%3Dy&name=Jack"))
  (= {"a" "b"} (parse-query-string "a=b"))
  (= {} (parse-query-string "")))

(deftest* url-encode-test
  (= "foo" (url-encode "foo"))
  (= "%25%2F" (url-encode "%/"))
  (= "bar" (url-encode :bar))
  (= "abc%26" (url-encode :abc&))
  (= "400" (url-encode 400))
  (#{"name=Jack&age=20&energy%26money=70%25%2F100%25"
     "name=Jack&energy%26money=70%25%2F100%25&age=20"
     "age=20&energy%26money=70%25%2F100%25&name=Jack"
     "age=20&name=Jack&energy%26money=70%25%2F100%25"
     "energy%26money=70%25%2F100%25&name=Jack&age=20"
     "energy%26money=70%25%2F100%25&age=20&name=Jack"}
   (url-encode {:name "Jack" :age 20 :energy&money "70%/100%"})))

(deftest* parse-http-language-tags-test
  (= {"da" 1 "en-gb" 0.8 "en" 0.7} (parse-http-language-tags "da, en-gb;q=0.8, en; q = 0.7"))
  (= {"en" 1} (parse-http-language-tags "en"))
  (= {"ja" 1} (parse-http-language-tags "ja, en;q=0"))
  (= {"ja" 0.7 "en" 0.5} (parse-http-language-tags "EN;q=0.5,Ja;q=0.7"))
  (= {} (parse-http-language-tags ""))
  (= {} (parse-http-language-tags nil)))

;; (run-tests)
