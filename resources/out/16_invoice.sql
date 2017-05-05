CREATE OR REPLACE PROCEDURE FILLINVOICE
IS
CURSOR invoices IS SELECT * FROM invoice;
vprice NUMBER;
resultPrice NUMBER;
tax NUMBER;
vdiscount NUMBER;
tmp NUMBER;
BEGIN
  FOR x IN invoices LOOP
    SELECT sum(SALES.QUANTITY * PRODUCT.PRICE) INTO vPRICE FROM SALES JOIN PRODUCT ON SALES.ID_PRODUCT = PRODUCT.ID_PRODUCT WHERE SALES.ID_INVOICE = x.ID_INVOICE;
    IF vPrice IS NOT NULL THEN
      tmp:= round(vPrice / 100);
      IF tmp > 1 THEN
        vdiscount:= round(tmp/100); 
      ELSE  
        vdiscount:= 0;
      END IF;
      tax:= round(vPrice * 0.23, 2);
      resultPrice:= round(vPrice + tax, 2);
      resultPrice:= round(resultPrice - (resultPrice * vdiscount),2);
      UPDATE INVOICE SET RESULT_PRICE = resultPrice, TAX_VALUE = tax, DISCOUNT = vdiscount WHERE ID_INVOICE = x.ID_INVOICE;
    END IF;
  END LOOP;
END;
/
EXEC FILLINVOICE;
--DELETE FROM INVOICE WHERE RESULT_PRICE is NULL;