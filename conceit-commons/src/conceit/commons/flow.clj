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
     (if (empty? bindings)
       then
       (let [pairs (partition 2 bindings)
             syms (map (comp gensym first) pairs)]
         `(let [[~@syms] (let [~(first syms) ~(second (first pairs))
                               ~(ffirst pairs) ~(first syms)
                               ~@(mapcat (fn [[previous-sym sym] [binding-var expr]] `(~sym (and ~previous-sym ~expr) ~binding-var ~sym))
                                         (partition 2 1 syms)
                                         (rest pairs))]
                           [~@syms])]
            (if ~(last syms)
              (let [~@(mapcat (fn [binding-var sym] `(~binding-var ~sym))
                              (map first pairs)
                              syms)]
                ~then)
              ~else)))))
  ([bindings then]
     `(if-let* ~bindings ~then nil)))
