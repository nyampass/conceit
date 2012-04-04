(ns conceit.commons.date
  (require [conceit.commons
            [class :as class]
            [map :as map]]
           [clojure
            [string :as string]])
  (import [java.util Date Calendar TimeZone]
          [java.text SimpleDateFormat]))
           
(defn now []
  (Date.))

(defn default-timezone []
  (TimeZone/getDefault))

(defn timezone [id]
  (TimeZone/getTimeZone id))

(defn calendar
  ([year month day hour minute sec millisec timezone]
     (doto (Calendar/getInstance)
       (.set year (dec month) day hour minute sec)
       (.set Calendar/MILLISECOND millisec)
       (.setTimeZone timezone)))
  ([year month day hour minute sec millisec]
     (calendar year month day hour minute sec millisec (default-timezone)))
  ([year month day hour minute sec]
     (calendar year month day hour minute sec 0))
  ([year month day]
     (calendar year month day 0 0 0)))

(defn calendar-from-date [date]
  (doto (Calendar/getInstance)
    (.setTime date)))

(defn date-unit [date unit]
  (.get (calendar-from-date date) unit))

(defn date-unit-fn [unit]
  (fn [date] (date-unit date unit)))

(def year (date-unit-fn Calendar/YEAR))
(defn month [date]
  (inc (date-unit date Calendar/MONTH)))
(def day (date-unit-fn Calendar/DAY_OF_MONTH))
(def hour (date-unit-fn Calendar/HOUR_OF_DAY))
(def minute (date-unit-fn Calendar/MINUTE))
(def sec (date-unit-fn Calendar/SECOND))
(def millisec (date-unit-fn Calendar/MILLISECOND))
(def weekday (date-unit-fn Calendar/DAY_OF_WEEK))

(defn date [& values]
  (.getTime (apply calendar values)))
  
(defn correct-date? [year month day]
  (try (.getTime (doto (calendar year month day)
                   (.setLenient false)))
       true
       (catch Exception _ false)))

(defn add-date-unit [date unit value]
  (.getTime (doto (calendar-from-date date)
              (.add unit value))))

(defn add-date-unit-fn [unit]
  (fn [date value] (add-date-unit date unit value)))

(def add-years (add-date-unit-fn Calendar/YEAR))
(def add-months (add-date-unit-fn Calendar/MONTH))
(def add-days (add-date-unit-fn Calendar/DAY_OF_MONTH))
(def add-hours (add-date-unit-fn Calendar/HOUR_OF_DAY))
(def add-minutes (add-date-unit-fn Calendar/MINUTE))
(def add-secs (add-date-unit-fn Calendar/SECOND))
(def add-millisecs (add-date-unit-fn Calendar/MILLISECOND))

(defn simple-date-format [pattern date]
  (.format (SimpleDateFormat. pattern) date))
