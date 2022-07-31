import Vue from 'vue'

import VoteArea from "../components/VoteArea";
import VoteResultDisplay from "../components/VoteResultDisplay";
import VueRouter from 'vue-router'

const originalPush = VueRouter.prototype.push

VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
Vue.use(VueRouter)
const routes = [
    {
        path:'/VoteArea',
        component:VoteArea
    },
    {
        path: '/VoteResultDisplay',
        component: VoteResultDisplay
    }
]
const router = new VueRouter({
    routes
})

export default router