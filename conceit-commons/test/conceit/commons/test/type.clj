(ns conceit.commons.test.type
  (use conceit.commons
       conceit.commons.test
       clojure.test))

(deftest* array-type-test
  (= (array-type :boolean) (type (make-array Boolean/TYPE 10)))
  (= (array-type :byte) (type (make-array Byte/TYPE 10)))
  (= (array-type :char) (type (make-array Character/TYPE 10)))
  (= (array-type :double) (type (make-array Double/TYPE 10)))
  (= (array-type :float) (type (make-array Float/TYPE 10)))
  (= (array-type :int) (type (make-array Integer/TYPE 10)))
  (= (array-type :long) (type (make-array Long/TYPE 10)))
  (= (array-type :short) (type (make-array Short/TYPE 10)))
  (= (array-type Boolean/TYPE) (type (make-array Boolean/TYPE 10)))
  (= (array-type Byte/TYPE) (type (make-array Byte/TYPE 100)))
  (= (array-type Character/TYPE) (type (make-array Character/TYPE 10)))
  (= (array-type Double/TYPE) (type (make-array Double/TYPE 10)))
  (= (array-type Float/TYPE) (type (make-array Float/TYPE 10)))
  (= (array-type Integer/TYPE) (type (make-array Integer/TYPE 10)))
  (= (array-type Long/TYPE) (type (make-array Long/TYPE 10)))
  (= (array-type Short/TYPE) (type (make-array Short/TYPE 10)))
  (= (array-type Object) (type (make-array Object 10)))
  (= (array-type String) (type (make-array String 10)))
  (= (array-type Integer) (type (make-array Integer 10)))
  (= (array-type Class) (type (make-array Class 10))))

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
