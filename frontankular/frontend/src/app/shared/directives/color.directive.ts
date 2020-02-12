import { Directive, ElementRef, OnInit, Renderer2, HostListener } from '@angular/core';

@Directive({
    selector:'[appColor]'
})
export class ColorDirective implements OnInit{

    constructor(private el: ElementRef, private renderer: Renderer2){}


ngOnInit(){
 this.renderer.setStyle(this.el.nativeElement, 'color', 'white')
}

@HostListener('click') click(event : Event){
    this.renderer.setStyle(this.el.nativeElement, 'color', 'white')
}

@HostListener('mouseenter') onMouseEnter(){
    this.renderer.setStyle(this.el.nativeElement,'color','#0bc5ef')
}

@HostListener('mouseleave') onMouseLeave(){
    this.renderer.setStyle(this.el.nativeElement,'color','white')
}
}