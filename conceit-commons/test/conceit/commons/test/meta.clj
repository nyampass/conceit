(ns conceit.commons.test.meta
  (use conceit.commons
       conceit.commons.test
       clojure.test))

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
