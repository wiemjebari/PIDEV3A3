<?php

namespace App\Form;

use App\Entity\Randonnees;
use phpDocumentor\Reflection\Types\False_;
use PhpParser\Node\Scalar\MagicConst\File;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType;

class RandonneesType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options )
    {
        $builder
            ->add('date',DateType::class, [

                'attr' => ['class' =>'form-control js-datepicker'],
                'label' =>'Date',
                'widget' =>'single_text',
            ])
            ->add('lieu')
            ->add('heureDepart')
            ->add('heureRetour')
            ->add('nbrPlaces')
            ->add('photo',
                FileType::class, [
                    'label' => 'Content( image)',
                    'mapped' => false,
                    'required'=> false,
                ]
            )
            ->add('prix')
            ;



    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Randonnees::class,
        ]);
    }
}
