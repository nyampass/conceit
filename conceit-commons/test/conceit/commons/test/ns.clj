(ns conceit.commons.test.ns
  (use conceit.commons
       conceit.commons.test
       clojure.test)
  (require conceit.commons.test.ns.sample1
           conceit.commons.test.ns.sample2))

(intern-from-var *ns* 'test-fn #'conceit.commons.test.ns.sample1/sample1-fn)
(intern-from-var *ns* 'test-value #'conceit.commons.test.ns.sample1/sample1-value)
(intern-from-var *ns* 'test-once #'conceit.commons.test.ns.sample1/sample1-once)
(intern-from-var *ns* 'test-struct #'conceit.commons.test.ns.sample1/sample1-struct)
(intern-from-var *ns* 'test-macro #'conceit.commons.test.ns.sample1/sample1-macro)

(deftest* intern-from-var-test
  (= [:sample1-fn 200] (test-fn 200))
  (= 42 test-value)
  (= 123 test-once)
  (= {:name "Jack" :age 20} (struct test-struct "Jack" 20))
  (= (list :sample1-macro 42) (test-macro 42)))

(intern-publics *ns* 'conceit.commons.test.ns.sample2)

(deftest* intern-publics-test
  (= [:sample2-fn 200] (sample2-fn 200))
  (= 420 sample2-value)
  (= 1230 sample2-once)
  (= {:price 1000 :color :white} (struct sample2-struct 1000 :white))
  (= (set [:sample2-macro 42]) (sample2-macro 42)))

;; (run-tests)
