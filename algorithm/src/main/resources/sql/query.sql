-- ## 595. 大的国家 ##
select name ,population,area
from World
where area >=3000000 or population >=25000000;

-- 175. 组合两个表
select  FirstName ,LastName , City , State
from Person p left join Address a on p.PersonId = a.PersonId

-- 1757. 可回收且低脂的产品
select product_id
from Products
where low_fats = 'Y' and recyclable = 'Y' ;

-- 176. 第二高的薪水
select  (
select salary
from Employee
group by salary
order by salary desc limit 1 offset 1  ) as SecondHighestSalary
-- limit 1 ,1

-- 183. 从不订购的客户
select Name as Customers
from Customers
where Id not in  (select CustomerId from Orders )

select c.Name as Customers
from Customers as c
     left join Orders as o on c.Id = o.CustomerId
where o.Id is null

-- 178. 分数排名
select a.score as score ,
       (select count(distinct b.score)  from  Scores b where b.score>=a.score) as 'rank'
    from Scores a
order by a.score desc;

-- rank() 并列后的是间隔排名 dense_rank() 并列后的是连续  row_number() 列号
select score , dense_rank() over(order by score desc) as 'rank'
from Scores  ;
-- 需要MYSQL 8 版本

-- 1683. 无效的推文
select tweet_id
from Tweets
where CHAR_LENGTH(content) >15 ;

-- 1378. 使用唯一标识码替换员工ID
select unique_id , name
from Employees e left join EmployeeUNI u on e.id = u.id

-- 1068. 产品销售分析 I
select product_name , 'year', price
from  Sales s left join  Products p on s.product_id = p.product_id

-- 1661. 每台机器的进程平均运行时间
select machine_id ,
       round( sum( case when activity_type = 'start' then -timestamp
                when activity_type = 'end' then timestamp else null end
           )/ count( distinct process_id)
        , 3 ) as processing_time
from Activity
group by machine_id ;

select machine_id ,round(avg(time) ,3) as processing_time
from (
    select machine_id , max(timestamp ) - min(timestamp ) as time
    from Activity
    group by machine_id , process_id
     ) a
group by  machine_id ;

-- 577. 员工奖金

select name , bonus
from Employee e left join Bonus b on e.empId = b.empId
where  bonus <1000 or bonus is null

-- 1280. 学生们参加各科测试的次数
select s.student_id , s.student_name , j.subject_name , count(e.student_id) as attended_exams
from Students s cross join Subjects j
    left join Examinations e on s.student_id = e.student_id and j.subject_name = e.subject_name
group by  s.student_id ,j.subject_name
    order by  s.student_id ,j.subject_name;


-- 570 至少有5名直接下属的经理
select name
from Employee t1 join
    ( select managerId
      from Employee
      group by  managerId
      having count(managerId) >=5 ) as t2
on t1.id = t2.managerId ;

-- 1934. 确认率
select t1.user_id ,
       NULLIF(Round( sum(case when t2.action = 'confirmed' then 1 else 0 end )
           / count( t2.action) , 2) , 0.00) as 'confirmation_rate'
from Signups t1
left join confirmations t2 on t1.user_id = t2.user_id
group by t1.user_id ;

-- 620. 有趣的电影
select  *
from cinema
where mod(id,2)=1 and description != 'boring'
order by rating desc ;

-- 1193. 每月交易 I
select date_format(trans_date,'%Y-%m') as month ,
    country , count(*) as trans_count ,
    count(if(state = 'approved' , 1, null)) as approved_count ,
    sum(amount) as trans_total_amount ,
    sum(if(state='approved' , amount , 0)) as approved_total_amount
from Transactions
group by month, country ;

--1173. 即时食物配送 I
select round(
                       (select count(*) from Delivery where order_date = customer_pref_delivery_date) /
                       (select count(*) from Delivery)  * 100
           ,2) as immediate_percentage ;

--1174. 即时食物配送 II

select round(
         sum(order_date = customer_pref_delivery_date)* 100  /
           count(*)  ,2) as immediate_percentage
from Delivery
where (customer_id , order_date) in (
    select customer_id , min(order_date) from delivery group by customer_id
    );

-- 534. 游戏玩法分析 III
select  player_id , event_date ,
        sum(games_played) over(partition by player_id order by event_date asc ) games_played_so_far
from Activity ;

-- 550. 游戏玩法分析 IV
select round(
    avg(a.event_date is not null) , 2
           ) fraction
from (
    select player_id , min(event_date) as login
    from activity
    group by player_id
     ) p
left join activity a
on p.player_id= a.player_id and datediff(a.event_date, p.login) =1 ;