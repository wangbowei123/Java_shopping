Java_shopping
=
Java网络商城相关源码文件及数据库表<br>
其中相关Java类存储在src中<br>
web中存放了相关jsp文件<br>
new_demo中存放相关mysql的数据库表<br>
具体设计逻辑如下：<br>
-
# 设计逻辑<br>
本项目为实现用户登陆注册、商品模拟购买、管理员增加删除商品的功能，包括用户密码的匹配，用户名的存储以及注册用户名与密码等多方面功能。以确保其可以满足实际生活中的绝大部分需要，即环境适应度较高。<br>
# 设计思路：<br>
>>本项目基于Java的web应用开发，同时配以后端数据库MySQL为信息存储工具，前端以jsp程序为核心，以便用于用户与程序的交互。基于此，程序设计过程中多次调用Java相关函数库以完成相关功能模块的设计。其具体分步如下
## 设计前端交互界面jsp程序：  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;此处用于设计注册与登录页面的相关内容，是用户与该系统进行交互的表层逻辑，其基于HTML5+CSS+JS，实现前端页面的设计开发。而js相关技术的使用，为其前端的数据与后端数据库的交互提供了接口。
## 设计后端数据交互模块
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;此部分总的设计目的是实现用户登陆注册所传递信息的后台处理，而为了便于开发与维护，将其分为四个子模块。<br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其中，首要处理模块为LoginServlet和SignupServlet的Java功能模块。此处为总体上的信息处理模块，系统将前端获取的数据信息送到此处，用于后续处理，也就是说，该部分模块是前端数据在后端的第一站。<br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其次，当各个数据信息进入LoginServlet和SignupServlet后，根据不同的功能，进入用户服务层，也就是UserService模块。此处若调用注册函数，则进一步传递，使用后续数据库；而若是登录函数，此处存在登录密码是否匹配的逻辑，同时也会进一步传递，向数据库延伸。<br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;另外，当需要调用后端数据库时，即进入UserDao管理层模块。此部分即为系统直接调用MySQL数据库的功能编写，基本采用实现MySQL的Java语法根据需要对数据库实现增删改查功能。但是数据表的构建不在这部分中体现，我们需要提前建立好合适的数据库表，此处仅是直接调用。<br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在商品购买与增删时，同样主要涉及到其在数据库中的存储，即包括商品在购买后其在商城中的数量减少，在个人仓库中的商品数量增加。同时包括管理员补充新货物与删除旧货物的过程。<br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;与之前设计不同的是，此处的部分用户交互界面是通过Java的servlet类来实现的，即通过Java间接引用html格式文件在界面上实现输出，实现信息的交互。即无需单独设计html页面，使得项目更加贴合于全程使用Java进行开发（因为我感觉前端页面如果过多则不太能体现一种面向对象的思路）。<br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在商品处理的模块部分中，其主要操作均是基于对数据库的处理，因此其较为模块化，此处不再赘述。<br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;由于后端处理基本上均涉及到数据库的使用，故而为了便于逻辑性的梳理以及后续的维护，同时也是体现Java的面向对象的特点。基于网上查阅到的相关Java web的构建矿建，该系统还设置了用户类User与数据库调用类DBUtils，前者是用于表征一个用户，其反映了该用户在使用该系统时所提供的各项数据，将其作为一个集合。而在后续实际使用中，我们仅需将这个用户类实体化成为一个对象即可。较为明显地体现了面向对象地逻辑特点。而DBUtils则是对数据库相关操作的一个集成，包括对数据库的连接，打开与关闭，将这部分提取继集成出来，一方面可以实现模块化，不会使其他的Java类过于臃肿，不利于维护阅读，同时将功能细分，即将这个DBUtils类作为一个功能钥匙，在后续使用中直接调用即可，更为方便。<br>
# 设计步骤：
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;前端页面的设计与开发。前端页面为本系统的主要交互页面，主要格式为jsp形式，其内核基于HTML5，根据需要设计出相关交互内容，包括用户登录注册页、部分模块中间过渡页、商品信息补充页等。通过基本的前端技术进行编写，以form表单形式进行数据后端提交。<br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;登陆注册后端处理模块。登陆注册的后端处理模块主要涉及到信息的存储，数据的匹配等。由于此处设计思路基于网络上找到的注册登录层次划分，故涉及到三个控制类，两个管理类。两个管理类是前端数据向后端传递的第一步，此时管理类接收到数据，继而转入服务层，实现相关操作。而在服务层就已经涉及到部分数据的存储，故而进入Dao层调用数据库实现相关操作即可。而由于各个模块大概率均要实现数据库的调用，故而将数据库的操作进行整合，实现了一个Dbutils类以此为钥匙实现直接调用，避免在后续各个Java模块中还要单独链接关闭数据库，实现面向对象的思路。<br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 商品购买与存储处理模块。该部分仅为模拟处理，即程序给出一个表格用于存储商品名，价格以及数量。当用户点击购买按钮后，该表格中的商品数量会减少1，而用户仓库中的商品将会增加。因此此处需要设计两个数据表一个用于存储商城中商品数据，另一个用于存储买家购买商品的数据表单。当用户登陆成功进入商城主页后，用户可根据需要点击对应商品的购买按钮，当按钮被点击后，该按钮所对应的商品信息将被传送到一个用于处理购买商品的Java模块中，在该模块中，程序将会访问商城数据库与仓库数据库，根据商品信息进行相关数据库的增删改查。实现购买商品的这一逻辑。<br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 管理员管理商品模块。此处为商城管理员实现补充或删除货物的操作设计。首先在登陆注册页管理员选择管理员登录模块，进入管理员主页，在该部分管理员可选择删除商品或增加商品，删除商品即删除商城数据库中对应的商品信息，并且实时的传递到商城主页。另外增加商品需要另外设计一个jsp文件，用于获取商品数据进行传递，以将数据出入商城数据库中，同样实时显示出来即可。<br>
