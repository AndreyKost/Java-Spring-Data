package mapping.demo.config;

import mapping.demo.data.entities.Employee;
import mapping.demo.data.services.dtos.EmployeeViewDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper(){

        ModelMapper modelMapper=new ModelMapper();

        Converter<String, String> stringConverter=new Converter<String, String>() {
            @Override
            public String convert(MappingContext<String, String> mappingContext) {
                return mappingContext.getSource() == null ? null : mappingContext.getSource().toLowerCase();
            }
        };


        PropertyMap<Employee, EmployeeViewDto> propertyMap=new PropertyMap<Employee, EmployeeViewDto>() {
            @Override
            protected void configure() {
                map().setAddress(source.getCity().getName());
            }
        };


        return modelMapper;
    }
}
