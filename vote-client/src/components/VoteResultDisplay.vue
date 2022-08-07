<template>
    <el-container class="vote-result-display">
        <el-table
                :data="voteResult"
                stripe
                style="width: 100%;">
            <el-table-column
                    prop="name"
                    label="姓名"
                    width="400">
            </el-table-column>
            <el-table-column
                    prop="score"
                    label="投票结果"
                    width="400"
            >
            </el-table-column>
        </el-table>
    </el-container>
</template>

<script>
    import axios from "axios";
    export default {
        name: "VoteResultDisplay",
        data(){
            return{
                voteResult:[],
            }
        },
        created() {
            this.servicemanChecked = window.sessionStorage.getItem("servicemanChecked")
            this.getVoteResult()

        },
        methods:{
            getVoteResult(){
                axios.post(`/voteResult`
                ).then(
                    (res)=> {
                        this.voteResult = res.data
                        // if(res.data == true){
                        //     console.log(res.data())
                        // }
                        // else {
                        //     // alert("没");
                        // }

                    }
                )
            }
        }
    }
</script>

<style scoped>

</style>