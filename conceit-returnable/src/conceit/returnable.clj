(ns conceit.returnable
  (:require conceit.returnable.Returning)
  (:import conceit.returnable.Returning))

(defn returning-point []
  (Object.))

(defn returner [returning-point]
  (fn [value] (throw (Returning. returning-point value))))

(defn returning-value [returning]
  (:value (.attributes returning)))

(defn apply-returning [returning returning-point]
  (if (= returning-point (:returning-point (.attributes returning)))
    (returning-value returning)
    (throw returning)))

(defmacro with-returner [returner-name & body]
  `(let [returning-point# (returning-point)]
     (try (let [~returner-name (returner returning-point#)]
            ~@body)
          (catch Returning returning# (apply-returning returning# returning-point#)))))
