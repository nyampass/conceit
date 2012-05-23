(ns conceit.commons.test.map
  (use conceit.commons
       conceit.commons.test
       clojure.test)
  (require [clojure.string :as string]))

(deftest* apply-with-map-test
  (= [:a 1 :b 2] (apply-with-map (fn [& args] args) {:a 1 :b 2}))
  (= [0 1 [:a 2 :b 3]] (apply-with-map (fn [x y & args] [x y args]) 0 1 {:a 2 :b 3}))
  (= nil (apply-with-map (fn [& args] args) {}))
  (= [1 nil] (apply-with-map (fn [x & args] [x args]) 1 {})))

(deftest* map-from-pairs-test
  (= {:a 1 :b 2 :c 3} (map-from-pairs [[:a 1] [:b 2] [:c 3]]))
  (= {:foo "FOO"} (map-from-pairs [[:foo "FOO"]]))
  (= {:foo 30 :bar 20} (map-from-pairs [[:foo 10] [:bar 20] [:foo 30]]))
  (= {:foo :bar} (map-from-pairs {:foo :bar}))
  (= {} (map-from-pairs [])))

(deftest* filter-map-test
  (= {:a 1 :c 3 :e 5} (filter-map odd? {:a 1 :b 2 :c 3 :d 4 :e 5}))
  (= {:b 2 :d 4} (filter-map even? {:a 1 :b 2 :c 3 :d 4 :e 5}))
  (= {} (filter-map #(> % 10) {:two 2 :three 3 :five 5 :nine 9 :one 1}))
  (= {:foo 42 :bar "BAR"} (filter-map identity {:hoge nil :foo 42 :bar "BAR" :piyo nil}))
  (= {1 "ONE" 2 "TWO" 3 "THREE"} (filter-map identity {1 "ONE" 2 "TWO" 3 "THREE"}))
  (= {} (filter-map odd? {})))

(deftest* filter-map-by-key-test
  (= {1 :a 3 :c 5 :e} (filter-map-by-key odd? {1 :a 2 :b 3 :c 4 :d 5 :e}))
  (= {2 :b 4 :d} (filter-map-by-key even? {1 :a 2 :b 3 :c 4 :d 5 :e}))
  (= {} (filter-map-by-key #(> % 10) {2 :two 3 :three 5 :five 9 :nine 1 :one}))
  (= {42 :foo "BAR" :bar} (filter-map-by-key identity {nil :hoge 42 :foo "BAR" :bar false :piyo}))
  (= {"ONE" 1 "TWO" 2 "THREE" 3} (filter-map-by-key identity {"ONE" 1 "TWO" 2 "THREE" 3}))
  (= {} (filter-map-by-key odd? {})))

(deftest* remove-map-test
  (= {:a 1 :c 3 :e 5} (remove-map even? {:a 1 :b 2 :c 3 :d 4 :e 5}))
  (= {:b 2 :d 4} (remove-map odd? {:a 1 :b 2 :c 3 :d 4 :e 5}))
  (= {} (remove-map #(< % 10) {:two 2 :three 3 :five 5 :nine 9 :one 1}))
  (= {:foo 42 :bar "BAR"} (remove-map nil? {:hoge nil :foo 42 :bar "BAR" :piyo nil}))
  (= {1 "ONE" 2 "TWO" 3 "THREE"} (remove-map nil? {1 "ONE" 2 "TWO" 3 "THREE"}))
  (= {} (remove-map identity {})))

(deftest* remove-map-by-key-test
  (= {1 :a 3 :c 5 :e} (remove-map-by-key even? {1 :a 2 :b 3 :c 4 :d 5 :e}))
  (= {2 :b 4 :d} (remove-map-by-key odd? {1 :a 2 :b 3 :c 4 :d 5 :e}))
  (= {} (remove-map-by-key #(< % 10) {2 :two 3 :three 5 :five 9 :nine 1 :one}))
  (= {42 :foo "BAR" :bar} (remove-map-by-key not {nil :hoge 42 :foo "BAR" :bar false :piyo}))
  (= {"ONE" 1 "TWO" 2 "THREE" 3} (remove-map-by-key nil? {"ONE" 1 "TWO" 2 "THREE" 3}))
  (= {} (remove-map-by-key identity {})))

(deftest* map-to-map-test
  (= {3 9 4 16 6 36} (map-to-map (fn [n] [n (* n n)]) [3 4 6]))
  (= {"name" "Jack" "age" "23"} (map-to-map #(string/split % #"=") ["name=Jack" "age=23"]))
  (= {"eman" "kcaJ" "ega" "32"} (map-to-map (fn [pair] (map #(apply str (reverse %))
                                                            (string/split pair #"="))) ["name=Jack" "age=23"]))
  (= {} (map-to-map #(fn [n] [n (* n n)]) [])))

(deftest* make-map-with-keys-by-test
  (= {"Jack" {:name "Jack" :age 23} "Mike" {:name "Mike" :age 19}} (make-map-with-keys-by :name [{:name "Jack" :age 23} {:name "Mike" :age 19}]))
  (= {23 {:name "Jack" :age 23} 19 {:name "Mike" :age 19}} (make-map-with-keys-by :age [{:name "Jack" :age 23} {:name "Mike" :age 19}]))
  (= {13 [10 3] 5 [2 3] 40 [18 22]} (make-map-with-keys-by #(apply + %) [[10 3] [2 3] [18 22]]))
  (= {} (make-map-with-keys-by :name [])))

(deftest* map-map-test
  (= {:a 2 :b 4 :c 6} (map-map #(* 2 %) {:a 1 :b 2 :c 3}))
  (= {10 20 20 30} (map-map #(+ 10 %) {10 10 20 20}))
  (= {} (map-map #(+ 10 %) [])))

(deftest* deep-merge-test
  (= {:a 10 :b 20 :c 30} (deep-merge {:a 10 :b 10} {:b 20 :c 30}))
  (= {:a {:a-a 10 :a-b 20}} (deep-merge {:a {:a-a 10}} {:a {:a-b 20}}))
  (= {:a {:a-a {:a-a-a 2
                :a-a-b 3}
          :a-b 5}
      :b {:b-a 6
          :b-b 8}}
     (deep-merge {:a {:a-a {:a-a-a 1
                            :a-a-b 2}}
                  :b {:b-a 6}}
                 {:a {:a-a {:a-a-a 2
                            :a-a-b 3}
                      :a-b 5}
                  :b {:b-b 8}}))
  (= {} (deep-merge {} {}))
  (= {:a 3} (deep-merge {:a {:a-a 1 :a-b 2}} {:a 3}))
  (= {:a 1 :b 2 :c 3} (deep-merge {:a 9 :c 3} {:b 8 :a 1} {:b 2})))

;; (run-tests)
