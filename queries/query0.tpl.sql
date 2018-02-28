SELECT partno, price, order_qty
FROM quotations Q1
WHERE Q1.partno IN
      (SELECT partno
       FROM invenrtory Q3
       WHERE Q3.onhand_qty < Q1.order_qty
             AND Q3.type = 'cpu');