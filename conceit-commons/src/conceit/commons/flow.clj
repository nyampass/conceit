(in-ns 'conceit.commons)
(clojure.core/use 'clojure.core)

(defmacro do0 [return-value & body]
  `(let [return-value# ~return-value]
     ~@body
     return-value#))

(defmacro ?-> [arg & cond-and-forms]
  (if (empty? cond-and-forms)
    arg
    `(?-> (if ~(first cond-and-forms) (-> ~arg ~(second cond-and-forms)) ~arg) ~@(nnext cond-and-forms))))

(defmacro ?->> [arg & cond-and-forms]
  (if (empty? cond-and-forms)
    arg
    `(?->> (if ~(first cond-and-forms) (->> ~arg ~(second cond-and-forms)) ~arg) ~@(nnext cond-and-forms))))

(defmacro ignore-exceptions [& body]
  `(try (do ~@body)
        (catch Exception _# nil)))
