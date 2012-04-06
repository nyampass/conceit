(ns conceit.commons.test.multimethod
  (use conceit.commons.multimethod
       conceit.commons.test
       clojure.test))

(defmulti test-multi-fn-1 type)
(defmethods test-multi-fn-1
  (String [s] [:string s])
  (Number [i] [:number i])
  (clojure.lang.Symbol [s] [:symbol s]))

(deftest* defmethods-test
  (= [:string "foo"] (test-multi-fn-1 "foo"))
  (= [:number 12] (test-multi-fn-1 12))
  (= [:symbol 'bar] (test-multi-fn-1 'bar)))

(defmulti* defmulti*-test-no-option :type)
(defmethod defmulti*-test-no-option :default [x]
  [:default x])

(defmulti* defmulti*-test-no-option-with-docstring "DOCSTRING" :n)
(defmethod defmulti*-test-no-option-with-docstring :default [x]
  [:default x])

(defmulti* defmulti*-test-no-option-with-attr-map {} type)
(defmethod defmulti*-test-no-option-with-attr-map :default [x]
  [:default x])

(defmulti* defmulti*-test-no-option-with-docstring-and-attr-map "doc string" {} (fn [x y] x))
(defmethod defmulti*-test-no-option-with-docstring-and-attr-map :default [x y]
  [:default x y])

(defmulti* defmulti*-test-default-option :type :default :foo)
(defmethod defmulti*-test-default-option :foo [x]
  [:foo x])
(defmethod defmulti*-test-default-option :default [x]
  [:default x])

(defmulti* defmulti*-test-hierarchy :x :hierarchy (ref (derive (make-hierarchy) :foooo :foo)))
(defmethod defmulti*-test-hierarchy :foo [x]
  [:foo x])
(defmethod defmulti*-test-hierarchy :bar [x]
  [:bar x])

(defmulti* defmulti*-test-methods identity
  :methods [(10 [x] :ju)
            (100 [x] :hyaku)
            (1000 [x] :sen)])

(defmulti* defmulti*-test-methods-hierarchy :x
  :hierarchy (ref (derive (make-hierarchy) :foooo :foo))
  :methods [(:foo [x]
                  [:f x])
            (:hoge [x]
                   [:h x])])

(deftest* defmulti*-test
  (= 1 (count (methods defmulti*-test-no-option)))
  (= [:default {:type 100 :value 50}] (defmulti*-test-no-option {:type 100 :value 50}))
  (= 1 (count (methods defmulti*-test-no-option-with-docstring)))
  (= [:default {:n 3}] (defmulti*-test-no-option-with-docstring {:n 3}))
  (= 1 (count (methods defmulti*-test-no-option-with-attr-map)))
  (= [:default 100] (defmulti*-test-no-option-with-attr-map 100))
  (= 1 (count (methods defmulti*-test-no-option-with-docstring-and-attr-map)))
  (= [:default 1 2] (defmulti*-test-no-option-with-docstring-and-attr-map 1 2))
  (= [:foo {:type 123}] (defmulti*-test-default-option {:type 123}))
  (= [:foo {:type :foo}] (defmulti*-test-default-option {:type :foo}))
  (= [:default {:type :default}] (defmulti*-test-default-option {:type :default}))
  (= [:foo {:x :foo}] (defmulti*-test-hierarchy {:x :foo}))
  (= [:foo {:x :foooo}] (defmulti*-test-hierarchy {:x :foooo}))
  (= [:bar {:x :bar}] (defmulti*-test-hierarchy {:x :bar}))
  (= :ju (defmulti*-test-methods 10))
  (= :hyaku (defmulti*-test-methods 100))
  (= :sen (defmulti*-test-methods 1000))
  (= [:f {:x :foo}] (defmulti*-test-methods-hierarchy {:x :foo}))
  (= [:f {:x :foooo}] (defmulti*-test-methods-hierarchy {:x :foooo}))
  (= [:h {:x :hoge}] (defmulti*-test-methods-hierarchy {:x :hoge})))

;; (run-tests)
