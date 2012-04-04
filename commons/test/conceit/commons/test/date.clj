(ns conceit.commons.test.date
  (use conceit.commons.date
       conceit.commons.test
       clojure.test)
  (import [java.util Date Calendar TimeZone]))

(deftest* now-test
  (let [current-date (Date.)
        now (now)]
    (>= (compare now current-date) 0)
    (< (- (.getTime now) (.getTime current-date)) 1000)))

(deftest* default-timezone-test
  (= (TimeZone/getDefault) (default-timezone)))

(deftest* timezone-test
  (= (TimeZone/getTimeZone "GMT") (timezone "GMT"))
  (= (TimeZone/getTimeZone "Asia/Tokyo") (timezone "Asia/Tokyo"))
  (= (TimeZone/getTimeZone "Europe/London") (timezone "Europe/London"))
  (= (TimeZone/getTimeZone "America/Los_Angeles") (timezone "America/Los_Angeles")))

(deftest* calendar-test
  (= (doto (Calendar/getInstance)
       (.set 1999 0 1 0 0 0)
       (.set Calendar/MILLISECOND 0)
       (.setTimeZone (TimeZone/getDefault)))
     (calendar 1999 1 1 0 0 0 0 (default-timezone)))
  (= (doto (Calendar/getInstance)
       (.set 2011 11 24 20 3 56)
       (.set Calendar/MILLISECOND 451)
       (.setTimeZone (timezone "GMT")))
     (calendar 2011 12 24 20 3 56 451 (timezone "GMT")))
  (= (calendar 2000 1 2 3 4 5 6 (default-timezone)) (calendar 2000 1 2 3 4 5 6))
  (= (calendar 2000 1 2 3 4 5 0 (default-timezone)) (calendar 2000 1 2 3 4 5))
  (= (calendar 2000 1 2 0 0 0 0 (default-timezone)) (calendar 2000 1 2)))

(deftest* calendar-from-date-test
  (= (doto (Calendar/getInstance)
       (.set 1985 0 25 11 23 45)
       (.set Calendar/MILLISECOND 20))
     (calendar-from-date (date 1985 1 25 11 23 45 20)))
  (= (doto (Calendar/getInstance)
       (.set 2031 6 21 23 41 2)
       (.set Calendar/MILLISECOND 33))
     (calendar-from-date (date 2031 7 21 23 41 2 33))))

(deftest* date-unit-test
  (= 2000 (date-unit (date 2000 3 4) Calendar/YEAR))
  (= 6 (date-unit (date 1977 7 6) (Calendar/MONTH)))
  (= 18 (date-unit (date 1991 9 18) (Calendar/DAY_OF_MONTH)))
  (= (+ 31 20) (date-unit (date 2001 2 20) Calendar/DAY_OF_YEAR))
  (= Calendar/AM (date-unit (date 1999 7 16 3 4 5) Calendar/AM_PM))
  (= Calendar/PM (date-unit (date 1999 7 16 13 4 5) Calendar/AM_PM)))

(deftest* date-unit-fn-tet
  (= 2000 ((date-unit-fn Calendar/YEAR) (date 2000 3 4)))
  (= Calendar/AM ((date-unit-fn Calendar/AM_PM) (date 1999 7 16 3 4 5))))

(deftest* year-test
  (= 1956 (year (date 1956 4 12 19 23 10 23)))
  (= 1999 (year (date 1999 11 4 7 1 1)))
  (= 2012 (year (date 2012 5 7))))

(deftest* month-test
  (= 4 (month (date 1985 4 23 11 30 21 345)))
  (= 1 (month (date 2012 1 6 19 0 0)))
  (= 12 (month (date 2112 12 24))))

(deftest* day-test
  (= 1 (day (date 2038 12 1 8 0 0 0)))
  (= 13 (day (date 1872 10 13 17 55 25)))
  (= 31 (day (date 1921 3 31)))
  (= 29 (day (date 2004 2 29 23 59 59 999)))
  (= 28 (day (date 2000 2 28 20 20 20))))

(deftest* hour-test
  (= 0 (hour (date 1999 9 6 0 10 20 30)))
  (= 6 (hour (date 1955 8 7 6 5 4)))
  (= 12 (hour (date 2000 5 18 12 20 33)))
  (= 18 (hour (date 2044 9 30 18 37 40 12)))
  (= 23 (hour (date 2100 10 10 23 59 59 999))))

(deftest* minute-test
  (= 0 (minute (date 2000 1 1 12 0 30 456)))
  (= 8 (minute (date 2345 10 20 3 8 17)))
  (= 29 (minute (date 1259 4 12 20 29 0 10)))
  (= 59 (minute (date 2031 12 3 23 59 8))))

(deftest* sec-test
  (= 0 (sec (date 1987 2 4 13 30 0 344)))
  (= 11 (sec (date 1993 8 12 14 27 11)))
  (= 34 (sec (date 2046 11 3 20 30 34 999)))
  (= 59 (sec (date 2100 5 19 1 4 59))))

(deftest* millisec-test
  (= 0 (millisec (date 1991 12 17 11 19 20 0)))
  (= 41 (millisec (date 1997 8 7 12 30 10 41)))
  (= 153 (millisec (date 2010 10 3 23 17 50 153)))
  (= 510 (millisec (date 2172 6 6 8 18 28 510)))
  (= 999 (millisec (date 1978 10 20 21 6 0 999))))

(deftest* weekday-test
  (= Calendar/SUNDAY (weekday (date 1967 11 5)))
  (= Calendar/MONDAY (weekday (date 2000 3 20)))
  (= Calendar/TUESDAY (weekday (date 1984 7 17)))
  (= Calendar/WEDNESDAY (weekday (date 1989 1 25)))
  (= Calendar/THURSDAY (weekday (date 2031 10 23)))
  (= Calendar/FRIDAY (weekday (date 2011 11 18)))
  (= Calendar/SATURDAY (weekday (date 1991 2 2))))

(deftest* date-test
  (= (.getTime (calendar 1985 1 25 11 24 8 13 (timezone "America/Los_Angeles"))) (date 1985 1 25 11 24 8 13 (timezone "America/Los_Angeles")))
  (= (.getTime (calendar 1985 1 25 11 24 8 13 (default-timezone))) (date 1985 1 25 11 24 8 13))
  (= (.getTime (calendar 1985 1 25 11 24 8 0 (default-timezone))) (date 1985 1 25 11 24 8))
  (= (.getTime (calendar 1985 1 25 0 0 0 0 (default-timezone))) (date 1985 1 25))
  (= (.getTime (calendar 1998 8 4 21 0 30 810 (timezone "Europe/London"))) (date 1998 8 4 21 0 30 810 (timezone "Europe/London")))
  (= (.getTime (calendar 1991 11 30 8 9 10 200 (default-timezone))) (date 1991 11 30 8 9 10 200))
  (= (.getTime (calendar 2014 7 10 18 57 21 0 (default-timezone))) (date 2014 7 10 18 57 21))
  (= (.getTime (calendar 2007 21 51 0 0 0 0 (default-timezone))) (date 2007 21 51))
  (= (.getTime (calendar 2003 3 1 0 0 0 0 (default-timezone))) (date 2003 2 29))
  (= (.getTime (calendar 2004 2 29 0 0 0 0 (default-timezone))) (date 2004 2 29)))

(deftest* correct-date?-test
  (true? (correct-date? 1985 1 25))
  (true? (correct-date? 2012 2 29))
  (true? (correct-date? 1970 11 30))
  (false? (correct-date? 1984 13 2))
  (false? (correct-date? 2031 2 29))
  (false? (correct-date? 2000 4 56)))

(deftest* add-date-unit-test
  (= (date 2010 1 3 4 7 12 560) (add-date-unit (date 2000 1 3 4 7 12 560) Calendar/YEAR 10))
  (= (date 2000 1 23 4 7 12 560) (add-date-unit (date 2000 1 3 4 7 12 560) Calendar/DAY_OF_MONTH 20))
  (= (date 1984 12 24 5 12 30) (add-date-unit (date 1984 12 24 12 12 30) Calendar/HOUR -7)))

(deftest* add-date-unit-fn-test
  (= (date 2010 1 3 4 7 12 560) ((add-date-unit-fn Calendar/YEAR) (date 2000 1 3 4 7 12 560) 10))
  (= (date 2000 1 23 4 7 12 560) ((add-date-unit-fn Calendar/DAY_OF_MONTH) (date 2000 1 3 4 7 12 560) 20))
  (= (date 1984 12 24 5 12 30) ((add-date-unit-fn Calendar/HOUR) (date 1984 12 24 12 12 30) -7)))

(deftest* add-years-test
  (= (date 1988 1 4) (add-years (date 1984 1 4) 4))
  (= (date 1945 2 16 3 4 5 60) (add-years (date 1956 2 16 3 4 5 60) -11)))

(deftest* add-months-test
  (= (date 2000 11 6) (add-months (date 2000 1 6) 10))
  (= (date 2001 1 6) (add-months (date 2000 1 6) 12))
  (= (date 1968 4 8 10 20 30) (add-months (date 1968 7 8 10 20 30) -3)))

(deftest* add-days-test
  (= (date 1988 12 30) (add-days (date 1988 12 10) 20))
  (= (date 1999 7 3) (add-days (date 1999 6 30) 3))
  (= (date 1984 5 28) (add-days (date 1984 6 1) -4)))

(deftest* add-hours-test
  (= (date 2000 2 3 23 8 9) (add-hours (date 2000 2 3 14 8 9) 9))
  (= (date 2000 1 4 3 5 6) (add-hours (date 2000 1 4 8 5 6) -5)))

(deftest* add-minutes-test
  (= (date 1997 10 20 10 45 30) (add-minutes (date 1997 10 20 10 23 30) 22))
  (= (date 1998 4 6 11 3 55) (add-minutes (date 1998 4 6 11 50 55) -47)))

(deftest* add-secs-test
  (= (date 2000 8 7 10 20 30) (add-secs (date 2000 8 7 10 20 1) 29))
  (= (date 1991 12 23 8 56 42) (add-secs (date 1991 12 23 8 56 49) -7)))

(deftest* add-millisecs-test
  (= (date 1987 3 15 17 42 30 813) (add-millisecs (date 1987 3 15 17 42 30 412) 401))
  (= (date 2010 5 16 20 23 40 112) (add-millisecs (date 2010 5 16 20 23 40 647) -535)))

(deftest* simple-date-format-test
  (= "2018.07.22" (simple-date-format "yyyy.MM.dd" (date 2018 7 22)))
  (= "2011/4/6 12:34:56" (simple-date-format "yyyy/M/d hh:mm:ss" (date 2011 4 6 12 34 56))))

;; (run-tests)
