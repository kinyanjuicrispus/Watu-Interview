SELECT vm.name AS vehicle_make, COUNT(a.id) AS total_sales
FROM `loan-schema`.`m_loan` ml
JOIN `asset-schema`.`asset` a ON ml.id = a.m_loan_id
JOIN `asset-schema`.`vehicle_model` vm ON a.model_id = vm.id
JOIN `asset-schema`.`vehicle_make` vmake ON vm.vehicle_make_id = vmake.id
WHERE ml.disbursedon_date BETWEEN '2020-01-01' AND '2020-02-29'
GROUP BY vehicle_make
HAVING total_sales > 1000;