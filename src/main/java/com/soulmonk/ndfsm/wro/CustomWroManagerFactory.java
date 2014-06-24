package com.soulmonk.ndfsm.wro;

import ro.isdc.wro.extensions.processor.js.CoffeeScriptProcessor;
import ro.isdc.wro.manager.factory.standalone.DefaultStandaloneContextAwareManagerFactory;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.model.resource.processor.decorator.ExtensionsAwareProcessorDecorator;
import ro.isdc.wro.model.resource.processor.factory.ProcessorsFactory;
import ro.isdc.wro.model.resource.processor.factory.SimpleProcessorsFactory;

/**
 * Company: Valpio
 * User: soulmonk
 * Date: 24.06.14
 * Time: 14:47
 */
public class CustomWroManagerFactory
    extends DefaultStandaloneContextAwareManagerFactory {
    @Override
    protected ProcessorsFactory newProcessorsFactory() {
        final SimpleProcessorsFactory factory = new SimpleProcessorsFactory();
        ResourcePreProcessor processor = ExtensionsAwareProcessorDecorator.decorate(new CoffeeScriptProcessor()).addExtension("coffee");
        factory.addPreProcessor(processor);
        return factory;
    }
}
