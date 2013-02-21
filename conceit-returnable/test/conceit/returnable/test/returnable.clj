(ns conceit.returnable.test.returnable
  (:use conceit.returnable
        clojure.test))

(deftest with-returner-test
  (is (= 10 (with-returner return 10)))
  (is (= 20 (with-returner return (return 20))))
  (is (= 30 (with-returner return (if true 30 (return 40)))))
  (is (= 40 (with-returner return (if false 30 (return 40)))))
  (is (= 50 (with-returner return
              (let [x 40]
                (+ x 10)))))
  (is (= 60 (with-returner return
              (let [x (return 60)]
                (+ x 10)))))
  (is (= 70 (with-returner return
              (let [x (try (throw (Exception.))
                           (catch Exception _ (return 70)))]
                (+ x 100)))))
  (is (= 170 (with-returner return
               (let [x (try 70
                            (catch Exception _ (return 70)))]
                 (+ x 100))))))
