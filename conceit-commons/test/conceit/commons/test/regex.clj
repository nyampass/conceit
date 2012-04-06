(ns conceit.commons.test.regex
  (use conceit.commons
       conceit.commons.test
       clojure.test))

(deftest* regex?-test
  (regex? #"a")
  (regex? #"")
  (regex? #"^[0-9]{5}$")
  (not (regex? "a"))
  (not (regex? #{1 2 3})))

(deftest* regex-from-string-test
  (re-seq (regex-from-string "hogepiyo") "hogepiyo")
  (re-seq (regex-from-string "foobar") "aaafoobar")
  (re-seq (regex-from-string "abawr783r1") "---abawr783r1---")
  (re-seq (regex-from-string "tttwwwtttwww") "bbbtttwwwtttwwwnnn")
  (re-seq (regex-from-string "a-zA-Z") "a-zA-Z")
  (not (re-seq (regex-from-string "hogepiyo") "piyohoge"))
  (not (re-seq (regex-from-string "a-zA-Z") "abcdefg")))

(deftest* regex-starts-with-test
  (re-seq (regex-starts-with "hoge") "hogepiyofoobar")
  (re-seq (regex-starts-with "hoge") "hoge")
  (re-seq (regex-starts-with "10") "102317914")
  (re-seq (regex-starts-with "10") "101628739")
  (not (re-seq (regex-starts-with "hoge") "aaaaaaa"))
  (not (re-seq (regex-starts-with "hoge") "aahoge")))
  
;; (run-tests)
