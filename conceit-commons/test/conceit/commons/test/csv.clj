(ns conceit.commons.test.csv
  (use conceit.commons.csv
       conceit.commons.test
       clojure.test))

(deftest* csv-value-test
  (= "\"foo\"" (csv-value "foo"))
  (= "\"abc\"" (csv-value :abc))
  (= "\"HOGE\"" (csv-value 'HOGE))
  (= "\"100\"" (csv-value 100))
  (= "\"hello \"\"world\"\"!\"" (csv-value "hello \"world\"!"))
  (= "\"foo, bar\"" (csv-value "foo, bar"))
  (= "\"\"" (csv-value ""))
  (= "\"\"" (csv-value nil)))

(deftest* csv-row-test
  (= "\"foo\",\"bar\"" (csv-row [:foo :bar]))
  (= "\"x\"" (csv-row ["x"]))
  (= "\"a,b\",\"c\"\"d\",\"efg\",\"10\"" (csv-row ["a,b" "c\"d" "efg" 10]))
  (= "\"x\",\"\"" (csv-row ["x" ""]))
  (= "" (csv-row [])))

(deftest* csv-rows-test
  (= "\"foo\",\"bar\"\r\n\"abc\",\"def\"\r\n" (csv-rows [[:foo :bar] ["abc" "def"]]))
  (= "\"name\",\"age\",\"note\"\r\n\"John\",\"24\",\"foo\"\"bar\"\r\n\"James\",\"30\",\"aaa,bbb,ccc\"\r\n" (csv-rows [[:name :age :note] ["John" 24 "foo\"bar"] ["James" 30 "aaa,bbb,ccc"]]))
  (= "\r\n\r\n" (csv-rows [[] []]))
  (= "" (csv-rows [])))

(deftest* csv-rows-by-maps-test
  (= "\"John\",\"24\",\"foo\"\"bar\"\r\n\"James\",\"30\",\"aaa,bbb,ccc\"\r\n" (csv-rows-by-maps [{:name "John" :age 24 :note "foo\"bar"} {:name "James" :age 30 :note "aaa,bbb,ccc"}] [:name :age :note]))
  (= "\"a\"\r\n\"b\"\r\n\"c\"\r\n" (csv-rows-by-maps [{:name :a :value 10} {:name :b :value 20} {:name :c :value 30}] [:name]))
  (= "\r\n\r\n" (csv-rows-by-maps [{:name :foo} {:name :bar}] []))
  (= "" (csv-rows-by-maps [] [:name :age])))

;; (run-tests)
