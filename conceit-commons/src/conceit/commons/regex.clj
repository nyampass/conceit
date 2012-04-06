(ns conceit.commons.regex
  (import java.util.regex.Pattern))

(defn regex? [obj]
  (instance? Pattern obj))

(defn regex-from-string [s]
  (Pattern/compile (Pattern/quote s)))

(defn regex-starts-with [s]
  (Pattern/compile (str "^" (Pattern/quote s))))
