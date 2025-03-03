SELECT vmake.name AS vehicle_make, 
       COUNT(a.id) AS total_sales
FROM `asset-schema`.`vehicle_make` vmake
LEFT JOIN `asset-schema`.`vehicle_model` vm ON vmake.id = vm.vehicle_make_id
LEFT JOIN `asset-schema`.`asset` a ON vm.id = a.model_id
LEFT JOIN `loan-schema`.`m_loan` ml ON a.m_loan_id = ml.id 
      AND ml.disbursedon_date BETWEEN '2020-01-01' AND '2020-02-29'
GROUP BY vehicle_make;