(ns conceit.commons.test.class
  (use conceit.commons.class
       conceit.commons.test
       clojure.test))

(deftest* static-field-value-test
  (= java.util.Calendar/MONDAY (static-field-value java.util.Calendar "MONDAY"))
  (= java.util.Calendar/MONDAY (static-field-value java.util.Calendar :MONDAY))
  (= java.util.Calendar/MONDAY (static-field-value java.util.Calendar 'MONDAY))
  (= java.util.Calendar/WEDNESDAY (static-field-value java.util.Calendar "WEDNESDAY"))
  (= String/CASE_INSENSITIVE_ORDER (static-field-value String :CASE_INSENSITIVE_ORDER))
  (= Character/MIN_VALUE (static-field-value Character 'MIN_VALUE))
  (= Byte/TYPE (static-field-value Byte "TYPE")))

;; (run-tests)
