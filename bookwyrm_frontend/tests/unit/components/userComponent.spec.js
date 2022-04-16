import {shallowMount, createLocalVue} from '@vue/test-utils'
import UserComponent from '@/components/UserComponent.vue'
import {createStore} from 'vuex'
import {createApp} from 'vue'


const store = createStore({
    data() {
    return {
        accountName: ""
    }
}
})
const app = createApp(UserComponent)
app.use(store)

describe('UserComponent.vue Test', ()=> {


    it('renders the component', ()=> {
        const wrapper = shallowMount(UserComponent, {
            global: {
                plugins: [store]
            }
        })
        expect(wrapper.vm.$options.name).toMatch('UserComponent')
    })

    it('processes valid prop data', ()=> {
        const wrapper = shallowMount(UserComponent, {
            global: {
                plugins: [store]
            }
        })
        wrapper.setData({accountName: 'johndoe'})
        expect(wrapper.vm.accountName).toMatch('johndoe')
    })
})