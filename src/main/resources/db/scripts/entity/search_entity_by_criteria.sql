select
     id,
     small_id,
     price,
     enum,
     some_date
from public.entity
    where id = :id
    and small_id = :small_id
    and price = :price
    and enum = :enum
    and some_date = :some_date
