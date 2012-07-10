(ns conceit.commons.multimethod)

(defmacro defmethods [name & method-definitions]
  `(do ~@(map (fn [method-definition]
                `(defmethod ~name ~@method-definition))
              method-definitions)))

(defmulti expand-defmulti*-option (fn [option-name option-arg defmulti-form] option-name)
  :default nil)

(defmethod expand-defmulti*-option nil [option-name option-arg defmulti-form]
  `(~@defmulti-form ~option-name ~option-arg))

(defmethod expand-defmulti*-option :methods [option-name option-arg defmulti-form]
  `(do ~defmulti-form
       (defmethods ~(second defmulti-form) ~@option-arg)))

(defmacro defmulti* [name & options]
  (let [[fixed-args [dispatch-fn & options]] (loop [fixed-args [] options options preds [string? map?]]
                                               (cond (empty? preds) [fixed-args options]
                                                     ((first preds) (first options)) (recur (conj fixed-args (first options))
                                                                                            (rest options)
                                                                                            (rest preds))
                                                     :else (recur fixed-args options (rest preds))))
        defmulti-form `(defmulti ~name ~@fixed-args ~dispatch-fn)]
    (reduce (fn [defmulti-form [option-name option-arg]]
              (expand-defmulti*-option option-name option-arg defmulti-form))
            defmulti-form
            (partition 2 options))))
