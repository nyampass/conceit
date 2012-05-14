(ns conceit.commons.test.file
  (use conceit.commons.file
       conceit.commons.test
       clojure.test
       [clojure.java.io :only [file]]))

;; ./test/test-directory/
;; a01/
;; a01/x10
;; a01/x11
;; a02/
;; a02/x10
;; b01/
;; b01/x10
;; b02/
;; b02/x10
;; c01/
;; c01/x10
;; c02/
;; c02/x10
;; c02/x11
;; c02/x12
;; test01
;; test02
;; test03

(deftest* recursive-children-test
  (= [(file "./test/test-directory/a01") (file "./test/test-directory/a01/x10") (file "./test/test-directory/a01/x11")
      (file "./test/test-directory/a02") (file "./test/test-directory/a02/x10")
      (file "./test/test-directory/b01") (file "./test/test-directory/b01/x10")
      (file "./test/test-directory/b02") (file "./test/test-directory/b02/x10")
      (file "./test/test-directory/c01") (file "./test/test-directory/c01/x10")
      (file "./test/test-directory/c02") (file "./test/test-directory/c02/x10") (file "./test/test-directory/c02/x11") (file "./test/test-directory/c02/x12")
      (file "./test/test-directory/test01")
      (file "./test/test-directory/test02")
      (file "./test/test-directory/test03")]
     (vec (recursive-children (file "./test/test-directory"))))
  (= [(file "./test/test-directory/a01/x10") (file "./test/test-directory/a01/x11")] (vec (recursive-children (file "./test/test-directory/a01"))))
  (nil? (recursive-children (file "./test/test-directory/test01")))
  (= [(file "./test/test-directory/a01")
      (file "./test/test-directory/a02")
      (file "./test/test-directory/b01")
      (file "./test/test-directory/b02")
      (file "./test/test-directory/c01") (file "./test/test-directory/c01/x10")
      (file "./test/test-directory/c02") (file "./test/test-directory/c02/x10") (file "./test/test-directory/c02/x11") (file "./test/test-directory/c02/x12")
      (file "./test/test-directory/test01")
      (file "./test/test-directory/test02")
      (file "./test/test-directory/test03")]
     (vec (recursive-children (file "./test/test-directory") #(re-seq #"^c" (.getName %))))))

;; (run-tests)
