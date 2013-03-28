(ns conceit.commons.test
  (use clojure.test))

(def ^{:private true} auto-assertion-symbols '#{= == < > <= >= not re-seq and or not-empty .equals})

(def ^{:private true} auto-assertion-symbol-regexes #{#"\?$" #"^\.?is[A-Z]"})

(defmulti auto-assertion-predicate? identity
  :default ::default)

(defmethod auto-assertion-predicate? ::default [head-of-form]
  (or (not (symbol? head-of-form))
      (auto-assertion-symbols head-of-form)
      (some #(re-seq % (name head-of-form)) auto-assertion-symbol-regexes)))

(defmacro set-auto-assertion-predicate [sym]
  `(defmethod ~`auto-assertion-predicate? '~sym [sym#] true))

(def ^{:private true} arg-count-ignored-of-auto-assertion-block-map '{let 1
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

(defmulti arg-count-ignored-of-auto-assertion-block identity
  :default ::default)

(defmethod arg-count-ignored-of-auto-assertion-block ::default [head-of-form]
  (arg-count-ignored-of-auto-assertion-block-map head-of-form))

(defmacro set-auto-assertion-block [sym arg-count-ignored]
  `(defmethod ~`arg-count-ignored-of-auto-assertion-block '~sym [sym#] ~arg-count-ignored))

(defn convert-with-assertion [form]
  (with-meta
    (if (or (not (list? form))
            (auto-assertion-predicate? (first form)))
      `(is ~form)
      (let [[first & body] form]
        (if-let [arg-count-ignored (arg-count-ignored-of-auto-assertion-block first)]
          `(~first ~@(take arg-count-ignored body) ~@(map convert-with-assertion (drop arg-count-ignored body)))
          form)))
    (meta form)))

(defmacro deftest* [name & body]
  `(deftest ~name ~@(map convert-with-assertion body)))

(defn run-test
  ([var ns]
     (binding [*report-counters* (ref *initial-report-counters*)] 
       (do-report {:type :begin-test-ns :ns ns})
       (let [once-fixture-fn (join-fixtures (:clojure.test/once-fixtures (meta ns)))
             each-fixture-fn (join-fixtures (:clojure.test/each-fixtures (meta ns)))]
         (once-fixture-fn (fn [] (each-fixture-fn (fn [] (test-var var))))))
       (do-report {:type :end-test-ns :ns ns})
       (let [summary (assoc @*report-counters* :type :summary)]
         (do-report summary)
         summary)))
  ([var]
     (run-test var *ns*)))
