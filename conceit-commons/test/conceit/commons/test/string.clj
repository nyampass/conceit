(ns conceit.commons.test.string
  (use conceit.commons.string
       conceit.commons.test
       clojure.test))

(deftest* non-empty-string?-test
  (true? (non-empty-string? "foo"))
  (true? (non-empty-string? "aehap9uwhefa"))
  (false? (non-empty-string? ""))
  (false? (non-empty-string? nil))
  (false? (non-empty-string? 123)))

(deftest* suffix-array-test
  (= ["hoge" "oge" "ge" "e"] (suffix-array "hoge"))
  (= [] (suffix-array ""))
  (= ["abcdefghijklmn" "bcdefghijklmn" "cdefghijklmn" "defghijklmn" "efghijklmn" "fghijklmn" "ghijklmn" "hijklmn" "ijklmn" "jklmn" "klmn" "lmn" "mn" "n"] (suffix-array "abcdefghijklmn"))
  (= ["a"] (suffix-array "a")))

;; (run-tests)
