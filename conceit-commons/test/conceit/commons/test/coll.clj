(ns conceit.commons.test.coll
  (use conceit.commons.coll
       conceit.commons.test
       clojure.test))

(deftest* get-some-test
  (= 10 (get-some {:a 10 :b 20 :c 30} :a :b))
  (= 20 (get-some {:a 10 :b 20 :c 30} :d :b :c))
  (= 30 (get-some {:a 10 :b 20 :c 30} :c :a :b))
  (= :A (get-some {"A" :A "B" :B} "A"))
  (= :A (get-some {"A" :A "B" :B} "D" "A"))
  (= nil (get-some {"A" :A "B" :B} "D" "C"))
  (= nil (get-some {"A" :A "B" :B}))
  (= :a (get-some [:a :b :c :d] 0 1 2))
  (= :b (get-some [:a :b :c :d] 7 19 1)))

(deftest* unique-test
  (= [1 2 3 4 5] (unique [1 1 2 2 3 3 4 4 5 5]))
  (= [1 2 3 4 5] (unique [1 2 3 3 4 5 1 2 3 4 5]))
  (= [5 4 3] (unique [5 4 3 5 4 3 5 4 3]))
  (= [1 2 3 4 5] (unique [1 2 3 4 5]))
  (= nil (unique []))
  (= ['a 'b 'c 'd 'e] (unique '(a b a c b d d e a)))
  (= ["foo" "BAR"] (unique #(.equalsIgnoreCase %1 %2) ["foo" "Foo" "BAR" "bar" "FOO"]))
  (= nil (unique #(.equalsIgnoreCase %1 %2) [])))

(deftest* unique-by-test
  (= [1 2 3] (unique-by #(Math/abs %) [1 -1 2 -2 3]))
  (= [1 2 3] (unique-by #(Math/abs %) [1 2 3 -1 -2 -3]))
  (= [3 2 1] (unique-by #(Math/abs %) [3 2 -2 1 -1 1]))
  (= [-1 2 -3] (unique-by #(Math/abs %) [-1 2 -3 3 2 1]))
  (= [1 2 3 4 5] (unique-by identity [1 2 3 4 5]))
  (= nil (unique-by identity []))
  (= [{:name :foo :score 10} {:name :bar :score 320}] (unique-by :name [{:name :foo :score 10} {:name :bar :score 320} {:name :foo :score 3}])))

;; (run-tests)
