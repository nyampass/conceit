(ns conceit.commons.test.number
  (use conceit.commons
       conceit.commons.test
       clojure.test))

(deftest* int-from-test
  (= 10 (int-from 10))
  (= -41 (int-from -41))
  (= 20 (int-from 20.34))
  (= -3 (int-from -3.14))
  (= Long/MAX_VALUE (int-from Long/MAX_VALUE))
  (= Long/MIN_VALUE (int-from Long/MIN_VALUE))
  (= 400 (int-from (BigDecimal. 400)))
  (= -500 (int-from (BigDecimal. -500)))
  (= 50 (int-from \2))
  (= 1 (int-from "1"))
  (= -30 (int-from "-30"))
  (= 102318741 (int-from "102318741"))
  (= Long/MAX_VALUE (int-from (str Long/MAX_VALUE)))
  (= Long/MIN_VALUE (int-from (str Long/MIN_VALUE)))
  (= nil (int-from "aa"))
  (= nil (int-from nil)))

(deftest* double-from-test
  (= 0.0 (double-from 0))
  (= 12.0 (double-from 12))
  (= -42.0 (double-from -42))
  (= 12.34 (double-from 12.34))
  (= -34.56 (double-from -34.56))
  (= Double/MAX_VALUE (double-from Double/MAX_VALUE))
  (= Double/MIN_VALUE (double-from Double/MIN_VALUE))
  (= (double Long/MAX_VALUE) (double-from Long/MAX_VALUE))
  (= (double Long/MIN_VALUE) (double-from Long/MIN_VALUE))
  (= (Double. "12.3456789") (double-from "12.3456789"))
  (= (Double. "-12.3456789") (double-from "-12.3456789"))
  (= 123.0 (double-from "123"))
  (= -123.0 (double-from "-123"))
  (= Double/MAX_VALUE (double-from (str Double/MAX_VALUE)))
  (= Double/MIN_VALUE (double-from (str Double/MIN_VALUE)))
  (= 0.0 (double-from "0"))
  (nil? (double-from "string"))
  (nil? (double-from nil)))

(deftest* in-range-test
  (= 10 (in-range 10))
  (= 0 (in-range 0))
  (= -30 (in-range -30))
  (= 10 (in-range 10 :max 30))
  (= 40 (in-range 100 :max 40))
  (= -40 (in-range -40 :max 15))
  (= 10 (in-range 10 :min 0))
  (= 0 (in-range -30 :min 0))
  (= -50 (in-range -123 :min -50))
  (= 0 (in-range -30 :min 0 :max 100))
  (= 100 (in-range 300 :min 0 :max 100))
  (= 55 (in-range 55 :min 0 :max 100)))

(deftest* in-range?-test
  (= true (in-range? 10))
  (= true (in-range? 0))
  (= true (in-range? 10 :max 20))
  (= true (in-range? -2 :max 5))
  (= true (in-range? 3 :min -20))
  (= true (in-range? 50 :min 8))
  (= true (in-range? 20 :min 10 :max 30))
  (= true (in-range? 0 :min -4 :max 4))
  (= false (in-range? 10 :max 5))
  (= false (in-range? 50 :max -20))
  (= false (in-range? 5 :min 10))
  (= false (in-range? 100 :min 200))
  (= false (in-range? 10 :min 20 :max 50))
  (= false (in-range? 60 :min 40 :max 50)))

;; (run-tests)