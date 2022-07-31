<template>
    <el-container class="vote-area" >

        <el-table
                :data="voteNames"
                stripe
                class="el-table-demo"
                style="width: 100%;">
            <el-table-column
                    prop="name"
                    class="el-table-demo-item"
                    label="姓名"
                    width="400">
            </el-table-column>
<!--            <el-table-column-->
<!--                    prop="score"-->
<!--                    label="投票记录"-->
<!--                    width="200"-->
<!--            >-->
<!--            </el-table-column>-->
            <el-table-column
                    class="el-table-demo-item"

                    label="投票"
                    width="400"
                    prop="score"

            >
                <template slot-scope="scope" >
                    <el-button type="success"  @click="voteDialog(scope.row.name)">投票</el-button>
                </template>

            </el-table-column>
        </el-table>
<template>
    <el-dialog
            title="提示"
            :visible.sync="voteDialogVisable"
            slot-scope="scope"
            width="30%"
            :before-close="handleClose(scope.row)">
        <template>
            <el-input-number v-model="num" @change="handleChange" :min="1" :max="10" label="描述文字"></el-input-number>
        </template>
        <span slot="footer" class="dialog-footer">
                        <el-button @click="voteDialogVisable = false">取 消</el-button>
                        <el-button type="primary" @click="voteDialogVisable = false">确 定</el-button>
                    </span>
    </el-dialog>
</template>
    </el-container>

</template>

<script>
    // import axios from "axios";
    import axios from "axios";

    export default {
        name: "VoteArea",
        data() {
            return{
                voteNames:[],
                name:'',
                num:1,
                activeIndex: '1',
                activeIndex2: '1',
                voteDialogVisable:false,
            }
        },
        created() {
            this.getVoteName()
        },
        methods:{

            sendToWebBack(){
                let url_data = this.$qs.stringify({
                    voteName:this.name,
                    score:this.num
                })
                axios.post(`/voteMessage`,
                    url_data

                ).then(
                    (res)=> {
                        if(res.data == true){
                            console.log(res.data())
                        }
                        else {
                            alert("投票失败");
                        }

                    }
                )
            },
            voteDialog(value){
                this.name=value
                this.voteDialogVisable=true
            },
            handleClose() {
                this.voteDialogVisable=false
            },
            handleChange(){
                this.sendToWebBack()
            },
            getVoteName(){
                axios.get(`/getVoteNames`

                ).then(
                    (res)=> {
                        this.voteNames=res.data
                        if(res.data == true){
                            console.log(res.data())
                        }
                        else {
                            alert("投票失败");
                        }

                    }
                )
            }

        }
    }
</script>

<style scoped>
.vote-area{
    background-color: #545c64;
    display: flex;
    align-items: center; /* 垂直居中 */
    justify-content: center; /* 水平居中 */
}
    .el-table-demo{
        /*width: 100%;*/
        /*display: flex;*/
        /*align-items: center; !* 垂直居中 *!*/
        /*justify-content: center; !* 水平居中 *!*/
    }
.el-table-demo-item{
    width: 500px;
    display: flex;
    align-items: center; /* 垂直居中 */
    justify-content: center; /* 水平居中 */
}
</style>