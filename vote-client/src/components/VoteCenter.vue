<template>
    <div class="vote-center">
        <el-header class="header ub main-justify cross-center top">
            <div class="header header-left-text">投票系统</div>
            <div class="header header-center-text">
                <el-menu
                        :default-active="activeIndex2"
                        class="el-menu-demo"
                        :router=true
                        mode="horizontal"
                        @select="handleSelect"
                        background-color="#545c64"
                        text-color="#fff"

                        active-text-color="#ffd04b">
                    <el-menu-item index="1" @click="toVoteArea">投票</el-menu-item>

                    <el-menu-item index="2"  @click="toVoteResultDisplay">查看结果</el-menu-item>
                  <el-menu-item  @click="reflesh">刷新</el-menu-item>
                </el-menu>
                <el-divider></el-divider>
            </div>
            <el-container >
                <el-main >
                    <!--路由占位符  -->
                    <router-view style="position: center"></router-view>
                </el-main>
            </el-container>
        </el-header>
<!--        <el-main class="content">-->


<!--        </el-main>-->

    </div>
</template>

<script>
    export default {
        name: "VoteCenter",
        data() {
            return{
                voteName:'zhangsan',
                score:'' ,
                activeIndex: '1',
                activeIndex2: '1'
            }
        },
        methods:{

            toVoteArea(){
                console.log("VoteArea")
                this.$router.push("/VoteArea")
            },
            toVoteResultDisplay(){
                this.$router.push("/VoteResultDisplay")
            },
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },
            reflesh(){
              location.reload()
            }
        }
    }
</script>   

<style scoped>
    .score{
        width: 200px;
    }
    .vote-center{
        width: 100%;
        height: 100%;
        margin: -8px -8px auto;

    }
    .header-left-text{

        color: #f4f6f8;
        /*left: 500px;*/
        font-size: 30px;
        font-weight: bolder;
        /*left: 20px;*/
        /*width: 200px;*/
        background-color: #545c64;


    }
    .top{
        /*padding-top: 001px;*/
        /*background-color: red;*/
    }
    .content{
        margin-top: 10px;
        background-color: #42b983;
        position: relative;
    }
    .header-center-text{

    }
    .el-menu-demo{
        display: flex;
        align-items: center; /* 垂直居中 */
        justify-content: center; /* 水平居中 */
    }
    .el-menu /deep/ .el-menu__inner{
        margin-left: 100px;
    }
    .el-menu-item /deep/ .el-menu-item__inner{
        margin-left: 100px;
    }
</style>