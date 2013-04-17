(ns conceit.commons.test.named
  (:use conceit.commons
        conceit.commons.test
        clojure.test))

(deftest* named?-test
  (true? (named? :KEYWORD))
  (true? (named? 'SYMBOL))
  (false? (named? "STRING"))
  (false? (named? [1 2 3]))
  (false? (named? {:a 1 :b 2}))
  (false? (named? 10))
  (false? (named? nil)))

(deftest* fullname-test
  (= "foo/bar" (fullname :foo/bar))
  (= "x/y" (fullname :x/y))
  (= "foo" (fullname :foo))
  (= "x" (fullname :x))
  (= "foo/bar" (fullname 'foo/bar))
  (= "x/y" (fullname 'x/y))
  (= "foo" (fullname 'foo))
  (= "bar" (fullname 'bar)))

(deftest* name-or-str-test
  (= "KEYWORD" (name-or-str :KEYWORD))
  (= "KEYWORD" (name-or-str ::KEYWORD))
  (= "SYMBOL" (name-or-str 'SYMBOL))
  (= "SYMBOL" (name-or-str `SYMBOL))
  (= "STRING" (name-or-str "STRING"))
  (= "[]" (name-or-str []))
  (= "10" (name-or-str 10))
  (= "" (name-or-str "")))

(deftest* fullname-or-str-test
  (= "KEYWORD" (fullname-or-str :KEYWORD))
  (= "foo/KEYWORD" (fullname-or-str :foo/KEYWORD))
  (= "SYMBOL" (fullname-or-str 'SYMBOL))
  (= "hoge/SYMBOL" (fullname-or-str 'hoge/SYMBOL))
  (= "STRING" (fullname-or-str "STRING"))
  (= "[]" (fullname-or-str []))
  (= "10" (fullname-or-str 10))
  (= "" (fullname-or-str "")))

;; (run-tests)