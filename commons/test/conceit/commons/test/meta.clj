(ns conceit.commons.test.meta
  (use conceit.commons.meta
       conceit.commons.test
       clojure.test))

(deftest* meta?-test
  (meta? {:a 10 :b 20})
  (meta? [1 2 3])
  (meta? (list :a :b :c))
  (not (meta? 100))
  (not (meta? :foo))
  (not (meta? "aaa"))
  (not (meta? (Object.))))

(deftest* assoc-meta-test
  (let [v (assoc-meta (with-meta {:a 50} {:x 1}) :y 2)]
    (= {:a 50} v)
    (= {:x 1 :y 2} (meta v)))
  (let [v (assoc-meta (with-meta ["A" "B" "C"] {}) :foo "abc")]
    (= ["A" "B" "C"] v)
    (= {:foo "abc"} (meta v)))
  (let [v (assoc-meta (with-meta () {:x "X"}) :y "Y" :z "Z")]
    (= () v)
    (= {:x "X" :y "Y" :z "Z"} (meta v))))

;; (run-tests)
