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

(deftest* with-meta*-test
  (= {:y 20} (with-meta* {:y 20} {:foo 1}))
  (= {:foo 1} (meta (with-meta* {:y 20} {:foo 1})))
  (= {} (with-meta* {} nil))
  (= nil (meta (with-meta* {} nil)))
  (= "" (with-meta* "" {:foo 1}))
  (= nil (meta (with-meta* "" {:foo 1}))))

(deftest* keep-meta-test
  (= {:y 20} (keep-meta (with-meta {:x 10} {:foo 1})
                        {:y 20}))
  (= {:foo 1} (meta (keep-meta (with-meta {:x 10} {:foo 1})
                               {:y 20})))
  (= {} (keep-meta (with-meta {} nil)
                   {}))
  (= nil (meta (keep-meta (with-meta {} nil)
                          {})))
  (= "" (keep-meta (with-meta {:x 10} {:foo 1})
                   ""))
  (= nil (meta (keep-meta (with-meta {:x 10} {:foo 1})
                          ""))))

;; (run-tests)
