package fr.lsi.gardsafe.exposition.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author Laurent SION
 *
 */
@Configuration
public class SpringFoxConfig implements BeanFactoryPostProcessor {

  @Override
  public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory)
      throws BeansException {
    BeanDefinition definition = beanFactory.getBeanDefinition("requestMappingHandlerAdapter");
    definition.setLazyInit(false);

  }

}
