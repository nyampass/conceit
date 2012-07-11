(ns conceit.commons.flow)

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

(defmacro when-let* [bindings & body]
  (if (empty? bindings)
    `(do ~@body)
    `(when-let [~@(take 2 bindings)]
       (when-let* [~@(drop 2 bindings)] ~@body))))

(defmacro if-let*
  ([bindings then else]
     (cond (empty? bindings) then
           (<= (count bindings) 2) `(if-let [~@bindings] ~then ~else)
           :else (let [else## (gensym :else)]
                   `(let [~else## (fn [] ~else)]
                      ~(reduce (fn [form binding]
                                 `(if-let [~@binding]
                                    ~form
                                    (~else##)))
                               then
                               (reverse (partition-all 2 bindings)))))))
  ([bindings then]
     `(if-let* ~bindings ~then nil)))
