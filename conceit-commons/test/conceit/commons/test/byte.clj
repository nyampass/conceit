(ns conceit.commons.test.byte
  (use conceit.commons
       conceit.commons.test
       clojure.test))

(deftest* bytes-from-test
  (= (vec (into-array Byte/TYPE [0 1 2 3 4 5])) (vec (bytes-from (into-array Byte/TYPE [0 1 2 3 4 5]))))
  (instance? (array-type :byte) (bytes-from (into-array Byte/TYPE [0 1 2 3 4 5])))
  (= (vec (.getBytes "Chunky Bacon!")) (vec (bytes-from "Chunky Bacon!")))
  (instance? (array-type :byte) (bytes-from "Chunky Bacon!"))
  (= (vec (into-array Byte/TYPE [0 1 2 3 4 5 6 7 8 9 10])) (vec (bytes-from [0 1 2 3 4 5 6 7 8 9 10])))
  (instance? (array-type :byte) (bytes-from [0 1 2 3 4 5 6 7 8 9 10]))
  (nil? (bytes-from 123)))

(deftest* hex-from-bytes-test
  (= "00100f0aff7f8081" (hex-from-bytes (into-array Byte/TYPE [0 16 15 10 -1 127 -128 -127]))))

(deftest* bytes-from-hex-test
  (= [0 16 15 10 -1 127 -128 -127] (vec (bytes-from-hex "00100f0aff7f8081"))))

;; (run-tests)
