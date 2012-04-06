(ns conceit.commons.test.test
  (use conceit.commons.test
       clojure.test))

(def ^{:dynamic true} dynamic-var)

(deftest* deftest*-test-1
  (= 10 (* 2 5))
  (= "foobar" (str "foo" "bar"))
  (= true (= 10 10))
  (= false (= 10 20))
  (is (= 10 (* 2 5)))
  (is (= "foobar" (str "foo" "bar")))
  (== 20 (* 10 2))
  (< 10 20)
  (> 30 15)
  (<= 5 30)
  ;; 10
  (>= 100 80)
  (not (string? 123))
  (re-seq #"aaa$" "bbbaaa")
  (and (= 2 (+ 1 1)) (= 3 (+ 1 2)))
  (or (= 2 (+ 1 1)) (= 3 (+ 1 1)))
  (not-empty [1 2 3])
  (empty? [])
  (nil? nil)
  (map? {})
  (vector? [])
  ;; 20
  (let [x 10]
    (= x 10)
    (= 20 (* x 2)))
  (when true
    (= 2 (+ 1 1))
    (= 3 (+ 1 2)))
  (when-not false
    (= 4 (* 2 2))
    (= "foobar" (str "foo" "bar")))
  (when-let [x 10]
    (= x 10)
    (= 20 (* x 2)))
  (when-first [x [1 2 3]]
    (= x 1)
    (= 5 (* 5 x)))
  ;; 30
  (if true
    (= 5 (+ 3 2))
    (= 6 (+ 3 2))) ;; will not be evaluated
  (if false
    (= 6 (+ 3 4)) ;; will not be evaluated
    (= 7 (+ 3 4)))
  (if-let [x 10]
    (= x 10)
    (= 20 (+ 10 9))) ;; will not be evaluated
  (if-let [x nil]
    (= x 10) ;; will not be evaluated
    (= 20 (* 2 10)))
  (if-not false
    (= 10 (* 2 5))
    (= 9 (* 2 5))) ;; will not be evaluated
  (do
    (string? "foobar")
    (not-empty "foobar")
    (map? {:a 10 :b 20}))
  (doseq [n [1 2 3]]
    (number? n))
  ;; 41
  (dotimes [n 3]
    (not (neg? n)))
  (binding [dynamic-var 20]
    (= dynamic-var 20)
    (= 40 (* dynamic-var 2)))
  (with-redefs [str 20]
    (= 20 str)
    (= 40 (* 2 str)))
  (with-local-vars [a 10 b 20]
    (= 10 @a)
    (= 20 @b)
    (var-set a 40)
    (= 40 @a)
    (var-set b 50)
    (= 50 @b))
  ;; 52
  (with-open [s (java.io.ByteArrayInputStream. (.getBytes "foo"))]
    (= (int \f) (.read s))
    (= (int \o) (.read s)))
  (let [x 10]
    (let [y 20]
      (= 30 (+ x y))))
  (thrown? Exception (Integer/parseInt "aaa"))
  (testing "test with testing"
    (= 10 (* 2 5))
    (= "abc" (str \a \b \c)))
  true
  (let [x 10]
    x)
  ;; 60
  )

;; (run-tests)
