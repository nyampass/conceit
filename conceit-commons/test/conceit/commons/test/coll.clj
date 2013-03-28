(ns conceit.commons.test.coll
  (use conceit.commons
       conceit.commons.test
       clojure.test))

(deftest* third-test
  (= 103 (third (range 101 200)))
  (= 'c (third '[a b c d e f g h i j k l m n o]))
  (nil? (third [1 2]))
  (nil? (third ())))

(deftest* fourth-test
  (= 104 (fourth (range 101 200)))
  (= 'd (fourth '[a b c d e f g h i j k l m n o]))
  (nil? (fourth [1 2]))
  (nil? (fourth ())))

(deftest* fifth-test
  (= 105 (fifth (range 101 200)))
  (= 'e (fifth '[a b c d e f g h i j k l m n o]))
  (nil? (fifth [1 2]))
  (nil? (fifth ())))

(deftest* sixth-test
  (= 106 (sixth (range 101 200)))
  (= 'f (sixth '[a b c d e f g h i j k l m n o]))
  (nil? (sixth [1 2]))
  (nil? (sixth ())))

(deftest* seventh-test
  (= 107 (seventh (range 101 200)))
  (= 'g (seventh '[a b c d e f g h i j k l m n o]))
  (nil? (seventh [1 2]))
  (nil? (seventh ())))

(deftest* eighth-test
  (= 108 (eighth (range 101 200)))
  (= 'h (eighth '[a b c d e f g h i j k l m n o]))
  (nil? (eighth [1 2]))
  (nil? (eighth ())))

(deftest* ninth-test
  (= 109 (ninth (range 101 200)))
  (= 'i (ninth '[a b c d e f g h i j k l m n o]))
  (nil? (ninth [1 2]))
  (nil? (ninth ())))

(deftest* tenth-test
  (= 110 (tenth (range 101 200)))
  (= 'j (tenth '[a b c d e f g h i j k l m n o]))
  (nil? (tenth [1 2]))
  (nil? (tenth ())))

(deftest* forty-second-test
  (= 142 (forty-second (range 101 200)))
  (nil? (forty-second [1 2]))
  (nil? (forty-second ())))

(deftest* append-test
  (= [1 2 3] (append [1 2] 3))
  (= [1 2 3] (append '(1 2) 3))
  (= [1 2 3 4] (append [1 2] 3 4))
  (= [1 2 3 4] (append '(1 2) 3 4))
  (= [:foo] (append [] :foo))
  (= [:bar] (append () :bar))
  (= [:baz] (append nil :baz))
  (= [:foo :bar] (append [] :foo :bar))
  (= [:foo :bar] (append () :foo :bar))
  (= [:foo :bar] (append nil :foo :bar)))

(deftest* prepend-test
  (= [3 1 2] (prepend [1 2] 3))
  (= [3 1 2] (prepend '(1 2) 3))
  (= [3 4 1 2] (prepend [1 2] 3 4))
  (= [3 4 1 2] (prepend '(1 2) 3 4))
  (= [:foo] (prepend [] :foo))
  (= [:bar] (prepend () :bar))
  (= [:baz] (prepend nil :baz))
  (= [:foo :bar] (prepend [] :foo :bar))
  (= [:foo :bar] (prepend () :foo :bar))
  (= [:foo :bar] (prepend nil :foo :bar)))

(deftest* flatten-1-test
  (= [1 2 3] (flatten-1 [[1] [2] [3]]))
  (= [1 2 3 4] (flatten-1 [[1 2] [3 4]]))
  (= [[1] [2] [3 4]] (flatten-1 [[[1] [2]] [[3 4]]]))
  (= [] (flatten-1 [[] [] []]))
  (= [[] []] (flatten-1 [[[] []] []]))
  (= [] (flatten-1 []))
  (= [] (flatten-1 nil)))

(deftest* contains-in?-test
  (true? (contains-in? {:a {:b 1 :c 2}} [:a :b]))
  (true? (contains-in? {:foo 10 :bar 20 :baz 30} [:foo]))
  (true? (contains-in? {:foo 1 :bar 2} []))
  (true? (contains-in? [:foo :bar :baz] [0]))
  (true? (contains-in? [:foo :bar :baz] [1]))
  (true? (contains-in? [[:foo :bar] [:hoge :piyo] [:a :b :c]] [1 0]))
  (true? (contains-in? 100 []))
  (true? (contains-in? {} []))
  (true? (contains-in? [] []))
  (false? (contains-in? {:a {:b {:c 1 :d 2}}} [:a :b :x]))
  (false? (contains-in? {:a {:b {:c 1 :d 2}}} [:a :y]))
  (false? (contains-in? {:a {:b {:c 1 :d 2}}} [:b :c]))
  (false? (contains-in? {} [:a]))
  (false? (contains-in? [:foo :bar :baz] [5])))

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
