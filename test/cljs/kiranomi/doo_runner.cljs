(ns kiranomi.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [kiranomi.core-test]))

(doo-tests 'kiranomi.core-test)

