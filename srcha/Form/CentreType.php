<?php

namespace App\Form;

use App\Entity\Centre;
use Symfony\Component\Form\AbstractType;
use PhpParser\Node\Scalar\MagicConst\File;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType;


class CentreType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom_centre')

            ->add('adresse')

            ->add('services')

            ->add('numTel')
            

            ->add('eMail')
            ->add('photo',filetype::class,[
                'label'=>'Content(image)',
                'mapped'=>'false',
                'required'=>false,
                'data_class' => null,
               
            ])
          
            
        ;
    }
    

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Centre::class,
        ]);
    }
}
