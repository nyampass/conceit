(ns conceit.commons.test.type
  (use conceit.commons.type
       conceit.commons.test
       clojure.test))

(deftest* derive*-test
  (let [hierarchy (make-hierarchy)]
    (not (isa? hierarchy :integer :number))
    (let [hierarchy (derive* hierarchy :integer :number)]
      (isa? hierarchy :integer :number)
      (not (isa? hierarchy :number :integer)))
    (let [hierarchy (derive* hierarchy :float :number :integer :number)]
      (isa? hierarchy :integer :number)
      (not (isa? hierarchy :number :integer))
      (isa? hierarchy :float :number)
      (not (isa? hierarchy :number :float))
      (not (isa? hierarchy :integer :float))
      (not (isa? hierarchy :float :integer)))
    (let [hierarchy (derive* hierarchy :positive-integer :integer :integer :number)]
      (isa? hierarchy :integer :number)
      (not (isa? hierarchy :number :integer))
      (isa? hierarchy :positive-integer :integer)
      (not (isa? hierarchy :integer :positive-integer))
      (isa? hierarchy :positive-integer :number)
      (not (isa? hierarchy :number :positive-integer))
      (not (isa? hierarchy :float :number))
      (let [hierarchy (derive* hierarchy :float :number)]
        (isa? hierarchy :float :number)
        (not (isa? hierarchy :number :float))))))

(deftest* make-hierarchy*-test
  (let [hierarchy (make-hierarchy* :integer :number)]
    (isa? hierarchy :integer :number)
    (not (isa? hierarchy :number :integer)))
  (let [hierarchy (make-hierarchy* :integer :number :positive-integer :integer)]
    (isa? hierarchy :integer :number)
    (not (isa? hierarchy :number :integer))
    (isa? hierarchy :positive-integer :integer)
    (not (isa? hierarchy :integer :positive-integer))
    (isa? hierarchy :positive-integer :number)
    (not (isa? hierarchy :number :positive-integer)))
  (= (make-hierarchy*) (make-hierarchy)))

;; (run-tests)
