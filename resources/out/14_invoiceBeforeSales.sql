INSERT INTO INVOICE(ID_INVOICE, RESULT_PRICE, TAX_VALUE, DISCOUNT)
SELECT
seq_invoice.NEXTVAL, null, null, null from dual connect by level <= 200000;