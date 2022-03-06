import {shallowMount} from '@vue/test-utils'
import BookBriefView from '@/components/BookBriefView.vue'

describe('BookBriefView.vue Test', ()=> {

    it('renders the component', ()=> {
        const wrapper = shallowMount(BookBriefView, {
            propsData:{
                'title': '',
                'author': ''
            }
        })
        expect(wrapper.vm.$options.name).toMatch('SearchBar')
    })
    it('processes valid prop data', ()=> {
        const wrapper = shallowMount(BookBriefView, {
            propsData:{
                'title': 'A Game of Thrones',
                'author': 'George R. R. Martin'
            }
        })

        expect(wrapper.vm.title).toMatch('A Game of Thrones')
        expect(wrapper.vm.author).toMatch('George R. R. Martin')
    })
})