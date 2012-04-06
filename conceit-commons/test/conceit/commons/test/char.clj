(ns conceit.commons.test.char
  (use conceit.commons
       conceit.commons.test
       clojure.test))

(deftest* char-range-test
  (= [\a \b \c \d \e] (char-range \a \e))
  (= [\G \H \I \J \K \L] (char-range \G \L))
  (= [\0 \1 \2 \3] (char-range \0 \3))
  (= [\r] (char-range \r \r))
  (= [\a \c \e] (char-range \a \e 2))
  (= [\8 \7 \6 \5 \4] (char-range \8 \4 -1))
  (= [\y] (char-range \y \y 10)))

;; (run-tests)
