(ns conceit.commons.test
  (use clojure.test))

(def ^{:private true} auto-assertion-symbols '#{= == < > <= >= not re-seq and or not-empty})

(def ^{:private true} auto-assertion-symbol-regexes #{#"\?$"})

(def ^{:private true} auto-assertion-body-forms-with-args-symbols '{let 1
                                                                    when 1
                                                                    when-not 1
                                                                    when-let 1
                                                                    when-first 1
                                                                    if 1
                                                                    if-let 1
                                                                    if-not 1
                                                                    do 0
                                                                    doseq 1
                                                                    dotimes 1
                                                                    binding 1
                                                                    with-redefs 1
                                                                    with-local-vars 1
                                                                    with-open 1
                                                                    letfn 1
                                                                    testing 1})

(defmulti ^{:private true} convert-with-assertion (fn [form] (if (list? form)
                                                               (first form)
                                                               ::not-list)))

(defmethod convert-with-assertion :default [form]
  (let [[first & body] form]
    (if (or (not (symbol? first))
            (auto-assertion-symbols first)
            (some #(re-seq % (name first)) auto-assertion-symbol-regexes))
      `(is ~form)
      (if-let [args (auto-assertion-body-forms-with-args-symbols first)]
        `(~first ~@(take args body) ~@(map convert-with-assertion (drop args body)))
        form))))

(defmethod convert-with-assertion ::not-list [form]
  `(is ~form))

(defmacro deftest* [name & body]
  `(deftest ~name ~@(map convert-with-assertion body)))
