import {shallowMount} from '@vue/test-utils'
import SearchBar from '@/components/SearchBar.vue'

describe('SearchBar.vue Test', ()=> {

    it('renders the component', ()=> {
        const wrapper = shallowMount(SearchBar, {
            data() {
                return {
                    searchString: ""
                }
            }
        })
        expect(wrapper.vm.$options.name).toMatch('SearchBar')
    })
    it('processes valid prop data', ()=> {
        const wrapper = shallowMount(SearchBar, {
            data() {
                return {
                    searchString: ""
                }
            }
        })
        wrapper.setData({searchString: 'Game of Thrones'})
        expect(wrapper.vm.searchString).toMatch('Game of Thrones')
    })
    
})