(in-ns 'conceit.commons)
(clojure.core/use 'clojure.core)

(defmacro do0 [return-value & body]
  `(let [return-value# ~return-value]
     ~@body
     return-value#))

(defmacro def-?arrow-macro [name base-macro]
  (let [val-sym (gensym :val)]
    `(defmacro ~name [init# & cond-and-forms#]
       (if (empty? cond-and-forms#)
         init#
         `(~'~name (if ~(first cond-and-forms#) (~'~base-macro ~init# ~(second cond-and-forms#)) ~init#) ~@(nnext cond-and-forms#))))))

(def-?arrow-macro ?-> ->)
(def-?arrow-macro ?->> ->>)
